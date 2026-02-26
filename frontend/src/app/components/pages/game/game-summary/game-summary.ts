import { Component, Input, OnInit, signal } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { Game } from '../../../../models/game.model';
import { User } from '../../../../models/user.model';

@Component({
  selector: 'app-game-summary',
  imports: [MatIconModule],
  templateUrl: './game-summary.html',
  styleUrl: './game-summary.scss',
})
export class GameSummary {
  // Attributes
  @Input() game!: Game | null;
}
