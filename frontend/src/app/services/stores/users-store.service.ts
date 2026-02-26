import { Injectable } from '@angular/core';
import { User } from '../../models/user.model';
import { AchievementProgress, AchievementProgressGroup } from '../../models/achievement.model';

const TTL = 30 * 60 * 1000; // Time To Life - 30 min

@Injectable({
  providedIn: 'root',
})
export class UsersStoreService {
  // List of users in memory for caching
  private users = new Map<string, User>();

  // Constructor
  constructor() {
    this.loadFromStorage();
  }

  // Methods
  // Add users in cached list
  setUsers(list: User[]) {
    list.forEach(newUser => {
      const id = newUser.steamId ?? '';
      const existingUser = this.users.get(id);

      if (existingUser) {
        this.users.set(id, { ...existingUser, ...newUser });
      } else {
        this.users.set(id, newUser);
      }
    });

    this.saveToStorage();
    console.debug("📦 List of users stored/updated");
  }

  // Add single user in cached list
  setUser(user: User) {
    const id = user.steamId ?? '';
    const existingUser = this.users.get(id);

    if (existingUser) {
      this.users.set(id, { ...existingUser, ...user });
    } else {
      this.users.set(id, user);
    }

    this.saveToStorage();
    console.debug("📦 Single user stored/updated");
  }

  // Get all users in cached list
  getUsers(): User[] {
    return Array.from(this.users.values());
  }

  // Get a single user in cached list
  getUser(userId: string): User | null {
    return this.users.get(userId) ?? null;
  }

  // Add user achievements
  setUserAchievements(userId: string, response: AchievementProgressGroup[]): void {
    const user = this.users.get(userId);
    if (!user) return;

    // Get the achievements that the user had or create a void map.
    const userAchievements = user.achievements ?? new Map<number, AchievementProgress[]>();

    // For each game in the response, we add it to the map.
    response.forEach(group => {
      userAchievements.set(group.gameApiId, group.achievements)
    });

    // Save the map in the user
    this.users.set(userId, {
      ...user,
      achievements: userAchievements
    })

    this.saveToStorage();
    console.debug("📦 User achievements stored");
  }

  // Add user achievement
  setUserAchievement(userId: string, response: AchievementProgressGroup): void {
    const user = this.users.get(userId);
    if (!user) return;

    // Get the achievements that the user had or create a void map.
    const userAchievements = user.achievements ?? new Map<number, AchievementProgress[]>();

    // Add the achievements to the map.
    userAchievements.set(response.gameApiId, response.achievements)

    // Save the map in the user
    this.users.set(userId, {
      ...user,
      achievements: userAchievements
    })

    this.saveToStorage();
    console.debug("📦 User single achievement group stored");
  }

  // Get user achievements for a single game
  getUserAchievements(userId: string, gameId: number): AchievementProgress[] | null {
    const user = this.users.get(userId);
    if (!user) return null;

    const achievements = user.achievements?.get(gameId);
    if (!achievements) return null;

    return achievements;
  }

  // Delete cached list
  clear() {
    this.users.clear();
    localStorage.removeItem('users_store');
  }

  // Save cached list in LocalStorage
  private saveToStorage() {
    const users = Array.from(this.users.entries());

    localStorage.setItem('users_store', JSON.stringify({
      users,
      expiry: Date.now() // Timestamp
    }
    ));
  }

  // Load cached list in LocalStorage
  private loadFromStorage() {
    const data = localStorage.getItem('users_store');
    if (!data) return;

    const parsed = JSON.parse(data);

    const now = Date.now()

    // Check Timestamp
    if (!parsed.expiry || now - parsed.expiry > TTL) {
      this.clear();
      return;
    }

    // Save data in memory
    parsed.users.forEach(([id, user]: [string, User]) => {
      if (user.achievements && !(user.achievements instanceof Map)) {
        const achievementsMap = new Map<number, AchievementProgress[]>(
          Object.entries(user.achievements).map(
            ([key, value]) => [Number(key), value as AchievementProgress[]]
          )
        );
        user.achievements = achievementsMap;
      }

      this.users.set(id, user);
    });
  }
}
