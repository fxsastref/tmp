import { Component, OnInit, signal } from '@angular/core';
import { User } from '../../../../models/user.model';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../../../../services/user.service';
import { AuthService } from '../../../../services/auth.service';
import { HttpErrorResponse } from '@angular/common/http';
import { ForeignUserBanner } from "../foreign-user-banner/foreign-user-banner";
import { ForeignUserTabs } from "../foreign-user-tabs/foreign-user-tabs";
import { Game } from '../../../../models/game.model';

@Component({
  selector: 'app-foreign-user-component',
  imports: [ForeignUserBanner, ForeignUserTabs],
  templateUrl: './foreign-user-component.html',
  styleUrl: './foreign-user-component.scss',
})
export class ForeignUserComponent implements OnInit {
  // Attributes
  userId!: string;
  user = signal<User | null>(null);
  games = signal<Game[] | null>(null);
  loading = signal(false);
  error = signal<HttpErrorResponse | null>(null);

  // Constructor
  constructor(private userService: UserService, private route: ActivatedRoute) { }

  // OnInit method
  ngOnInit(): void {
    // Get the userId passed as a parameter
    this.route.paramMap.subscribe(params => {
      this.userId = params.get('userId') ?? '';
    });

    // Get the user by userId
    if (this.userId) {
      this.getUserSummary();
      this.getUserGames();
    }
  }

  // Methods
  // Get user summary
  getUserSummary(): void {
    this.loading.set(true);
    this.error.set(null);

    // Service call
    this.userService.getUserSummary(this.userId).subscribe({
      next: (data) => {
        this.user.set(data);
        this.loading.set(false);
      },

      error: (err) => {
        this.error.set(err);
        this.loading.set(false);
      }
    })
  }

  // Get user games
  getUserGames(): void {
    this.loading.set(true);
    this.error.set(null);

    // Service call
    this.userService.getUserGames(this.userId).subscribe({
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
