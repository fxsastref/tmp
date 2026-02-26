import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, of, pipe, tap, throwError } from 'rxjs';
import { Category, Game, GamesPageResponse, Genre, } from '../models/game.model';
import { GamesStoreService } from './stores/games-store.service';
import { AchievementGroup } from '../models/achievement.model';

@Injectable({
  providedIn: 'root',
})
export class GameService {
  // Attributes
  private apiUrl = "http://localhost:8080/games";

  // Constructor
  constructor(private http: HttpClient, private gamesStore: GamesStoreService) { }

  // Methods
  // Get a list of 20 games
  getGamesPage(page: number): Observable<GamesPageResponse> {
    return this.http.get<GamesPageResponse>(`${this.apiUrl}/?page=${page}`).pipe(
        tap(response => {this.gamesStore.addPage(page, response.content)}), // Save the content in the Store
        catchError(this.handleError)
      );
  }

  // Get a single game
  getGame(gameId: number): Observable<Game> {
    // Check if the game is cached in the GameStore and if it are full loaded
    const cached = this.gamesStore.getGame(gameId);
    if (cached && cached.isFullLoaded) {
      return of(cached);
    }

    // If it is not cached, make the request
    return this.http.get<Game>(`${this.apiUrl}/${gameId}`).pipe(
      tap(response => {this.gamesStore.setGame(response)}), // Save the content in the Store
      catchError(this.handleError)
    )
  }

  // Get game achievements
  getGameAchievements(gameId: number): Observable<Game> {
    return this.http.get<AchievementGroup>(`${this.apiUrl}/${gameId}/achievements`).pipe(
      tap(response => { this.gamesStore.setGameAchievements(response)}), // Save the content in the Store

      // Return the updated game
      map(() => {
        const game = this.gamesStore.getGame(gameId);
        if (!game) {
          throw new Error('Game not found after updating achievements');
        }
        return game;
      }),
      
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
