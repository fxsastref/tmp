import { Component, signal } from '@angular/core';
import { Game } from '../../../../models/game.model';
import { GameCard } from "../../../common/game-card/game-card";
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { GamesStoreService } from '../../../../services/stores/games-store.service';

@Component({
  selector: 'app-games-browse-games-list',
  imports: [GameCard, ReactiveFormsModule],
  templateUrl: './games-browse-games-list.html',
  styleUrl: './games-browse-games-list.scss',
})
export class GamesBrowseGamesList {
  // Attributes
  error = signal<HttpErrorResponse | null>(null);
  loading = signal<boolean>(false);
  searchText = new FormControl('');

  // Constructor
  constructor(private gameStore: GamesStoreService) { }

  // Search games by name
  searchGames(): Game[] | void {
    const value = this.searchText.value;
    if (!value) return

    const q = String(value).toLowerCase();
    return this.gameStore.searchGames(q);
  }
}
