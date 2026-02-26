import { Component, Input } from '@angular/core';
import { Game } from '../../../models/game.model';
import { MatIcon } from '@angular/material/icon';
import { RouterLink } from "@angular/router";

@Component({
  selector: 'app-game-card',
  imports: [MatIcon, RouterLink],
  templateUrl: './game-card.html',
  styleUrl: './game-card.scss',
})
export class GameCard {
  // Game received from parent component
  @Input() game!: Game;
}
