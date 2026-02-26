import { Component, Input, OnChanges, OnInit, signal } from '@angular/core';
import { AchievementCardListed } from '../../../common/achievement-card-listed/achievement-card-listed';
import { Game } from '../../../../models/game.model';
import { User } from '../../../../models/user.model';
import { GameService } from '../../../../services/game.service';
import { HttpErrorResponse } from '@angular/common/http';
import { UserService } from '../../../../services/user.service';
import { Achievement, AchievementProgress } from '../../../../models/achievement.model';

@Component({
  selector: 'app-game-achievements',
  imports: [AchievementCardListed],
  templateUrl: './game-achievements.html',
  styleUrl: './game-achievements.scss',
})
export class GameAchievements implements OnChanges {
  // Attributes
  @Input() game!: Game | null;
  @Input() user!: User | null;
  loading = signal(true);
  error = signal<HttpErrorResponse | null>(null);

  achievements = signal<Achievement[]>([]);
  achievementsProgress = signal<AchievementProgress[]>([]);

  // Constructor
  constructor(private gameService: GameService, private userService: UserService) { }

  // onInit method
  ngOnChanges(): void {
      this.getGameAchievements();
      this.getUserAchievements();

  }

  // Methods
  // Get game achievements
  getGameAchievements(): void {
    if (!this.game) return

    this.loading.set(false);
    this.error.set(null);

    // Service call
    this.gameService.getGameAchievements(this.game?.apiId).subscribe({
      next: (data) => {
        this.game = data;
        this.achievements.set(data.achievements ?? [])
        this.loading.set(false);
      },

      error: (err) => {
        this.error.set(err);
        this.loading.set(false);
      }
    })
  }

  // Get user achievements
  getUserAchievements(): void {
    if (!this.user?.steamId) return
    if (!this.game?.apiId) return
    
    this.loading.set(false);
    this.error.set(null);

    // Service call
    this.userService.getUserAchievements(this.user.steamId, this.game?.apiId).subscribe({
      next: (data) => {
        this.achievementsProgress.set(data);
        this.loading.set(false);
      },

      error: (err) => {
        this.error.set(err);
        this.loading.set(false);
      }
    })
  }
}
