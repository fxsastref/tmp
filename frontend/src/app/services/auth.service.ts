import { Injectable, OnInit, signal } from '@angular/core';
import { BehaviorSubject, catchError, Observable, tap, throwError } from 'rxjs';
import { LoginRequest, LoginResponse, MessageResponse, RegisterRequest, User } from '../models/user.model';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { UsersStoreService } from './stores/users-store.service';
import { GamesStoreService } from './stores/games-store.service';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  // Attributes
  private apiUrl = 'http://localhost:8080/auth';
  private tokenKey = 'auth_token';

  // State
  private currentUserSubject = new BehaviorSubject<User | null>(null);
  currentUser$ = this.currentUserSubject.asObservable();
  isAuthenticated = signal<boolean>(!!localStorage.getItem('auth_token'));

  // Constructor
  constructor(private http: HttpClient, private router: Router, private gamesStore: GamesStoreService, private userStore: UsersStoreService) { }

  // Methods
  register(request: RegisterRequest): Observable<MessageResponse> {
    return this.http.post<MessageResponse>(`${this.apiUrl}/register`, request).pipe(
      catchError(this.handleError)
    );
  }

  login(request: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.apiUrl}/login`, request).pipe(
      tap(response => this.handleLoginSuccess(response)),
      catchError(this.handleError)
    );
  }

  logout(): void {
    localStorage.clear();
    this.currentUserSubject.next(null);
    this.isAuthenticated.set(!!localStorage.getItem('auth_token'));
    this.router.navigate(['/login']);
  }

  updateUser(user: User): void {
    this.currentUserSubject.next(user);
  }

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  getCurrentUser(): User | null {
    return this.currentUserSubject.value;
  }

  isAdmin(): boolean {
    const user = this.currentUserSubject.value;
    return user?.role === 'ADMIN';
  }

  linkSteamAccount(request: string): Observable<MessageResponse> {
    return this.http.put<MessageResponse>(`${this.apiUrl}/linking`, request).pipe(
      catchError(this.handleError)
    )
  }

  // Login success handler
  private handleLoginSuccess(response: LoginResponse): void {
    // Save token
    localStorage.setItem(this.tokenKey, response.token);

    // Create user
    const user: User = {
      username: response.username,
      email: response.email,
      role: response.role,
      enabled: response.enabled
    };

    // Save data
    this.updateUser(user);
    this.isAuthenticated.set(!!localStorage.getItem('auth_token'));
  }

  // Errors handler
  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'Error';

    if (error.error instanceof ErrorEvent) {
      // Client error
      errorMessage = `Error: ${error.error.message}`
    } else {
      // Server error
      errorMessage = `Error code: ${error.status}, message: ${error.error.body.detail}`;
    }

    return throwError(() => ({
      status: error.status,
      message: errorMessage,
      error: error.error
    }))
  }
}
