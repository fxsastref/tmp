import { Component, Input, signal } from '@angular/core';
import { Category, Game, Genre } from '../../../../models/game.model';
import { GameCard } from "../../../common/game-card/game-card";
import { AuthService } from '../../../../services/auth.service';

@Component({
  selector: 'app-home-games-list',
  imports: [GameCard],
  templateUrl: './home-games-list.html',
  styleUrl: './home-games-list.scss',
})
export class HomeGamesList {
  // Attributes
  @Input() games!: Game[] | null

  // Attributes fors filters
  genres = signal<Genre[] | null>(null);
  categories = signal<Category[] | null>(null);
  publishers = signal<string[] | null>(null);
  developers = signal<string[] | null>(null);

  genreFilter = signal<string | null>(null);
  categoryFilter = signal<string | null>(null);
  publisherFilter = signal<string | null>(null);
  developerFilter = signal<string | null>(null);
}
