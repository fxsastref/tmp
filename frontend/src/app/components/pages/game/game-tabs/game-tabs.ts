import { Component, Input } from '@angular/core';
import { GameAchievements } from '../game-achievements/game-achievements';
import { GameSummary } from '../game-summary/game-summary';
import { MatTab, MatTabGroup } from '@angular/material/tabs';
import { Game } from '../../../../models/game.model';
import { User } from '../../../../models/user.model';

@Component({
  selector: 'app-game-tabs',
  imports: [MatTabGroup, MatTab, GameSummary, GameAchievements],
  templateUrl: './game-tabs.html',
  styleUrl: './game-tabs.scss',
})
export class GameTabs {
  // Attributes
  @Input() game!: Game | null;
  @Input() user!: User | null;
}
