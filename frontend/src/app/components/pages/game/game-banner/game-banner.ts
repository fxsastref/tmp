import { Component, Input } from '@angular/core';
import { Game } from '../../../../models/game.model';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-game-banner',
  imports: [MatIconModule],
  templateUrl: './game-banner.html',
  styleUrl: './game-banner.scss',
})
export class GameBanner {
  // Attributes
  @Input() game!: Game | null;
}
