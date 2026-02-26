import { Component, Input, OnInit, signal } from '@angular/core';
import { User } from '../../../../models/user.model';
import { MatIconModule } from '@angular/material/icon';
import { Game } from '../../../../models/game.model';
import { UserService } from '../../../../services/user.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-foreign-user-banner',
  imports: [MatIconModule],
  templateUrl: './foreign-user-banner.html',
  styleUrl: './foreign-user-banner.scss',
})
export class ForeignUserBanner implements OnInit {
  // Attributes
  error = signal<HttpErrorResponse | null>(null);

  @Input() user!: User | null;
  @Input() games!: Game[] | null;

  totalGames = signal<number | null>(null);
  totalAchievements = signal<number | null>(null);

  // Constructor
  constructor(private userService: UserService) { }

  // OnInit method
  ngOnInit(): void {
    this.countGames();
    this.countAchievements();
  }

  // Methods
  // Get the total games
  countGames(): void {
    if (this.user?.steamId) return void

    this.userService.countUserGames(this.user?.steamId).subscribe({
      next: (data) => {
        this.totalGames.set(data);
      },

      error: (err) => {
        this.error.set(err);
        console.log(err);
      }
    })
  }

  // // Get the total achievementes
  countAchievements(): void {
    if (this.user?.steamId) return void
    
    this.userService.countUserAchievements(this.user?.steamId).subscribe({
      next: (data) => {
        this.totalAchievements.set(data);
      },

      error: (err) => {
        this.error.set(err);
        console.log(err);
      }
    })
  }
}