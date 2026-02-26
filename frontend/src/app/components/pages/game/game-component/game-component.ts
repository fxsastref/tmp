import { Component, signal } from '@angular/core';
import { GameBanner } from '../game-banner/game-banner';
import { GameTabs } from '../game-tabs/game-tabs';
import { Game } from '../../../../models/game.model';
import { User } from '../../../../models/user.model';
import { ActivatedRoute } from '@angular/router';
import { UsersStoreService } from '../../../../services/stores/users-store.service';
import { GameService } from '../../../../services/game.service';
import { HttpErrorResponse } from '@angular/common/http';
import { UserService } from '../../../../services/user.service';

@Component({
  selector: 'app-game-component',
  imports: [GameBanner, GameTabs],
  templateUrl: './game-component.html',
  styleUrl: './game-component.scss',
})
export class GameComponent {
  // Attributes
  error = signal<HttpErrorResponse | null>(null);
  game = signal<Game | null>(null);
  user = signal<User | null>(null);
  gameId!: number
  userId!: string;

  // Constructor
  constructor(private route: ActivatedRoute, private gameService: GameService, private userStore: UsersStoreService, private userService: UserService) { }

  // OnInit method
  ngOnInit(): void {
    // Get the user ID passed as a parameter
    this.route.paramMap.subscribe(params => {
      this.gameId = Number(params.get('gameId'));
      this.userId = params.get('userId') ?? '';
    });

    // Load game and user if exist
    this.loadGame(this.gameId);
    if (this.userId) { this.loadUser(this.userId) }
  }

  // Load game by gameId
  loadGame(gameId: number): void {
    this.error.set(null);

    this.gameService.getGame(gameId).subscribe({
      next: (data) => {
        this.game.set(data);
      },
      
      error: (err) => {
        this.error.set(err);
        console.log(err);
      }
    })
  }

  // Load user by userId
  loadUser(userId: string): void {
    this.error.set(null);

    this.userService.getUserSummary(userId).subscribe({
      next: (data) => {
        this.user.set(data);
      },

      error: (err) => {
        this.error.set(err)
      }
    })
  }
}
