import { Component, signal } from '@angular/core';
import { AccountBanner } from "../account-banner/account-banner";
import { AccountTabs } from "../account-tabs/account-tabs";
import { HttpErrorResponse } from '@angular/common/http';
import { User } from '../../../../models/user.model';
import { AuthService } from '../../../../services/auth.service';
import { UserService } from '../../../../services/user.service';
import { Game } from '../../../../models/game.model';

@Component({
  selector: 'app-account-component',
  imports: [AccountBanner, AccountTabs],
  templateUrl: './account-component.html',
  styleUrl: './account-component.scss',
})
export class AccountComponent {
  // Attributes
  loading = signal(false);
  error = signal<HttpErrorResponse | null>(null);
  user = signal<User | null>(null);
  games = signal<Game[] | null>(null);

  // Constructor
  constructor(private authService: AuthService, private userService: UserService) { };

  // OnInit method
  ngOnInit(): void {
    this.loadUser();
    this.getOwnSummary();
    this.getOwnGames();
  }

  // Methods
  // Load current user logged
  loadUser(): void {
    this.error.set(null);

    // Service call
    this.authService.currentUser$.subscribe({
      next: (data) => {
        this.user.set(data);
      },

      error: (err) => {
        this.error.set(err);
      }
    })
  }

  // Update current user with Steam info
  getOwnSummary(): void {
    this.error.set(null);

    // Service call
    this.userService.getOwnSummary().subscribe({
      error: (err) => {
        this.error.set(err);
      }
    })
  }

  // Get all user games
  getOwnGames(): void {
    this.loading.set(true);
    this.error.set(null);

    // Service call
    this.userService.getOwnGames().subscribe({
      next: (data) => {
        this.games.set(data);
        this.loading.set(false);
      },

      error: (err) => {
        this.error.set(err);
        this.loading.set(false);
      }
    })
  }
}
