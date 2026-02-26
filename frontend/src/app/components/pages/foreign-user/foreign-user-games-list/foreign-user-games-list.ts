import { Component, Input, signal } from '@angular/core';
import { Category, Game, Genre } from '../../../../models/game.model';
import { GameCardListed } from "../../../common/game-card-listed/game-card-listed";
import { User } from '../../../../models/user.model';

@Component({
  selector: 'app-foreign-user-games-list',
  imports: [GameCardListed],
  templateUrl: './foreign-user-games-list.html',
  styleUrl: './foreign-user-games-list.scss',
})
export class ForeignUserGamesList {
  // Attributes
  @Input() games!: Game[] | null;
  @Input() user!: User | null;
}
