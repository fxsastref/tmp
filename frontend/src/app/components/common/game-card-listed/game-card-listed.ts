import { Component, Input } from '@angular/core';
import { Game } from '../../../models/game.model';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { RouterLink } from "@angular/router";

@Component({
  selector: 'app-game-card-listed',
  imports: [MatIconModule, MatProgressBarModule, RouterLink],
  templateUrl: './game-card-listed.html',
  styleUrl: './game-card-listed.scss',
})
export class GameCardListed {
  // Game received from parent component
  @Input() game!: Game;
  @Input() userId?: string;
}
