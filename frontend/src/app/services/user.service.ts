import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, of, tap, throwError } from 'rxjs';
import { User } from '../models/user.model';
import { AuthService } from './auth.service';
import { Game } from '../models/game.model';
import { GamesStoreService } from './stores/games-store.service';
import { UsersStoreService } from './stores/users-store.service';
import { AchievementGroup, AchievementProgress, AchievementProgressGroup } from '../models/achievement.model';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  // Attributes
  private apiUrl = 'http://localhost:8080/users';

  // Constructor
  constructor(private http: HttpClient, private authService: AuthService, private gamesStore: GamesStoreService, private userStore: UsersStoreService) { }

  // Methods

  // --- Authenticated User ---//
  //
  // Methods focused for the logged in user
  //
  // ---//

  // Get the Steam summary for the logged-in user.
  getOwnSummary(): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/me/summary`).pipe(
      tap(response => {
        this.userStore.setUser(response); // Update in memory list
        this.authService.updateUser(response); // Update in memory current user
      }),
      catchError(this.handleError)
    )
  }

  // Get all Steam games for the logged-in user
  getOwnGames(): Observable<Game[]> {
    return this.http.get<Game[]>(`${this.apiUrl}/me/games`).pipe(
      tap(response => this.gamesStore.setGames(response, true)), // Update in memory list
      catchError(this.handleError)
    )
  }

  // Get all Steam achievements for the logged-in user
  getOwnAchievements(): Observable<User> {
    // Get userId for the logged-in user
    const userId = this.getOwnUserId();
    if (!userId) {
      return throwError(() => new Error('User not logged in'));
    }

    return this.http.get<AchievementProgressGroup[]>(`${this.apiUrl}/me/achievements`).pipe(
      tap(response => { this.userStore.setUserAchievements(userId, response) }), // Save the content in the Store
      // Return the updated user
      map(() => {
        const user = this.userStore.getUser(userId);
        if (!user) {
          throw new Error('User not found in store');
        }
        return user;
      }),
      catchError(this.handleError)
    )
  }

  // Get the userId for the logged-in user
  getOwnUserId(): string | undefined {
    console.log(this.authService.getCurrentUser()?.steamId);
    return this.authService.getCurrentUser()?.steamId;
  }

  // Get the total games for the logged-in user
  countOwnUserGames(): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/me/count/games`).pipe(
      catchError(this.handleError)
    )
  }

  // Get the total achievements for the logged-in user
  countOwnUserAchievements(): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/me/count/achievements`).pipe(
      catchError(this.handleError)
    )
  }

  // --- Specified User ---//
  //
  // Methods focused for requesting data using a specified userId
  //
  // ---//

  // Get a specified user summary
  getUserSummary(userId: string): Observable<User> {
    // Check if the user is cached in the UserStore and if it are full loaded
    const cached = this.userStore.getUser(userId);
    if (cached) {
      return of(cached);
    }

    // If it is not cached, make the request
    return this.http.get<User>(`${this.apiUrl}/${userId}/summary`).pipe(
      tap(response => { this.userStore.setUser(response) }), // Update in memory list
      catchError(this.handleError)
    )
  }

  // Get a specified user games
  getUserGames(userId: string): Observable<Game[]> {
    return this.http.get<Game[]>(`${this.apiUrl}/${userId}/games`).pipe(
      tap(response => this.gamesStore.setGames(response, true)), // Update in memory list
      catchError(this.handleError)
    )
  }

  // Get a specified user achievements
  getUserAchievements(userId: string, gameId: number): Observable<AchievementProgress[]> {
    // Check if the achievements is cached in the UserStore
    const cached = this.userStore.getUserAchievements(userId, gameId);
    if (cached) {
      return of(cached);
    }

    // If it is not cached, make the request
    return this.http.get<AchievementProgressGroup>(`${this.apiUrl}/${userId}/games/${gameId}/achievements`).pipe(
      tap(response => this.userStore.setUserAchievement(userId, response)),
      map(response => { return response.achievements }),
      catchError(this.handleError),
    )
  }

  // Get a specified user total games
  countUserGames(userId: string): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/${userId}/count/games`).pipe(
      catchError(this.handleError)
    )
  }

  // Get a specified user total achievements
  countUserAchievements(userId: string): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/${userId}/count/achievements`).pipe(
      catchError(this.handleError)
    )
  }

  // --- General ---//
  //
  // Methods focused for be using by all methods
  //
  // ---//

  // Search users by triad
  searchUsers(request: string): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiUrl}/search?searchParam=${request}`).pipe(
      tap(response => { this.userStore.setUsers(response) }),
      catchError(this.handleError)
    )
  }

  // Errors handler
  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'Error';

    if (error.error instanceof ErrorEvent) {
      // Client error
      errorMessage = `Error: ${error.error.message}`
    } else {
      // Server error
      errorMessage = `Error code: ${error.status}, message: ${error.message}`;
    }

    return throwError(() => ({
      status: error.status,
      message: errorMessage,
      error: error.error
    }))
  }
}
