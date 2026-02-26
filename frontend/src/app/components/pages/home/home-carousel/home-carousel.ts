import { AfterViewInit, Component, Input, OnChanges } from '@angular/core';
import { Game } from '../../../../models/game.model';
import { initFlowbite } from 'flowbite';
import { RouterLink } from "@angular/router";

@Component({
  selector: 'app-home-carousel',
  imports: [RouterLink],
  templateUrl: './home-carousel.html',
  styleUrl: './home-carousel.scss',
})
export class HomeCarousel implements OnChanges, AfterViewInit{
  // Attributes
  @Input() games!: Game[] | null
  randomGames: Game[] = [];

  // OnChange method
  ngOnChanges() {
    this.getRandomGames();
    if (this.randomGames.length > 0) {
      setTimeout(() => initFlowbite());
    }
  }

  ngAfterViewInit() {
    if (this.randomGames.length > 0) {
      setTimeout(() => initFlowbite());
    }
  }

  // Methods
  // Get 5 random games from the entire list
  getRandomGames(): void {
    if (this.games && this.games.length > 0) {
      this.randomGames = [...this.games]
        .sort(() => Math.random() - 0.5)
        .slice(0, 5);
    }
  }
}
