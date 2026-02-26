import { Component, Inject, Input } from '@angular/core';
import { GameCardListed } from "../../../common/game-card-listed/game-card-listed";
import { Game } from '../../../../models/game.model';
import { AuthService } from '../../../../services/auth.service';
import { User } from '../../../../models/user.model';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { GamesStoreService } from '../../../../services/stores/games-store.service';

@Component({
  selector: 'app-account-games-list',
  imports: [GameCardListed, ReactiveFormsModule],
  templateUrl: './account-games-list.html',
  styleUrl: './account-games-list.scss',
})
export class AccountGamesList {
  // Attributes
  @Input() games!: Game[] | null;
  @Input() user!: User | null;
  @Input() loading = false;
  searchText = new FormControl('');

  // Constructor
  constructor(private gamesStore: GamesStoreService) { }

  // Search games by name
  searchGames(): Game[] | null {
    const value = this.searchText.value;
    if (!value) return this.games

    const q = String(value).toLowerCase();
    return this.gamesStore.searchGames(q);
  }
}
