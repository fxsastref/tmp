import { Injectable } from '@angular/core';
import { Game } from '../../models/game.model';
import { AchievementGroup } from '../../models/achievement.model';

const TTL = 30 * 60 * 1000; // Time To Life - 30 min

@Injectable({
  providedIn: 'root',
})
export class GamesStoreService {
  // List of games in memory for caching
  private games = new Map<number, Game>();
  // List of pages number
  private loadedPages = new Set<number>();

  // Constructor
  constructor() {
    this.loadFromStorage();
  }

  // Methods
  // Add a list of games and the number of the page requested in memory
  addPage(page: number, list: Game[]) {
    list.forEach(game => {
      const existing = this.games.get(game.apiId);

      this.games.set(game.apiId, {
        ...existing,
        ...game,
        isFullLoaded: existing?.isFullLoaded ?? false
      });
    });

    this.loadedPages.add(page);
    this.saveToStorage();
    console.debug("📦  List of games and page stored");
  }

  // Check if the page is loaded
  isPageLoaded(page: number): boolean {
    return this.loadedPages.has(page);
  }

  // Get de last loaded page
  getLastLoadedPage(): number {
    if (this.loadedPages.size === 0) return 0;
    return Math.max(...Array.from(this.loadedPages));
  }

  // Adds a list of games to the cached list and marks each game as FullLoaded or not (depending on the parameter).
  setGames(list: Game[], isFull: boolean = false) {
    list.forEach(game => {
      const existing = this.games.get(game.apiId);

      this.games.set(game.apiId, {
        ...existing,
        ...game,
        isFullLoaded: isFull || existing?.isFullLoaded || false
      });
    });

    this.saveToStorage();
    console.debug("📦  List of games stored");
  }

  // Add a single game to the cached list and marks it as FullLoaded
  setGame(game: Game) {
    this.games.set(game.apiId, {
      ...game,
      isFullLoaded: true
    });

    this.saveToStorage();
    console.debug("📦  Full game stored");
  }

  // Get all games in cached list
  getGames(): Game[] {
    return Array.from(this.games.values());
  }

  // Get a single game in cached list
  getGame(gameId: number): Game | null {
    return this.games.get(gameId) ?? null;
  }

  // Seach games by name
  searchGames(q: string): Game[] {
    return Array.from(this.games.values()).filter(game => game.name.toLowerCase().includes(q));
  }

  // Add game achievements
  setGameAchievements(response: AchievementGroup) {
    const game = this.games.get(response.gameApiId);
    if (!game) return;

    this.games.set(response.gameApiId, {
      ...game,
      achievements: response.achievements
    });

    this.saveToStorage();
    console.debug("📦 Game achievements stored");
  }

  // Delete cached list
  clear(): void {
    this.games.clear();
    localStorage.removeItem('games_store');
  }

  // Save cached list in LocalStorage
  private saveToStorage() {
    const games = Array.from(this.games.entries());
    const pages = Array.from(this.loadedPages);

    localStorage.setItem('games_store', JSON.stringify({
      games,
      pages,
      expiry: Date.now() // Timestamp
    }));
  }

  // Load cached list in LocalStorage
  private loadFromStorage() {
    // Get LocalStorage data
    const data = localStorage.getItem('games_store');
    if (!data) return;

    const parsed = JSON.parse(data);

    const now = Date.now();

    // Check Timestamp
    if (!parsed.expiry || now - parsed.expiry > TTL) {
      this.clear();
      return;
    }

    // Save data in memory
    parsed.games.forEach(([id, game]: [number, Game]) => {
      this.games.set(id, game);
    });

    parsed.pages.forEach((page: number) => {
      this.loadedPages.add(page);
    });
  }
}
