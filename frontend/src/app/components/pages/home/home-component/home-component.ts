import { Component, ElementRef, HostListener, OnInit, signal, ViewChild } from '@angular/core';
import { HomeGamesList } from "../home-games-list/home-games-list";
import { HomeCarousel } from "../home-carousel/home-carousel";
import { Game } from '../../../../models/game.model';
import { GameService } from '../../../../services/game.service';
import { HttpErrorResponse } from '@angular/common/http';
import { GamesStoreService } from '../../../../services/stores/games-store.service';

@Component({
  selector: 'app-home-component',
  imports: [HomeGamesList, HomeCarousel],
  templateUrl: './home-component.html',
  styleUrl: './home-component.scss',
})
export class HomeComponent implements OnInit {
  // Attributes
  games = signal<Game[] | null>(null);
  error = signal<HttpErrorResponse | null>(null);
  loading = signal(false);

  currentPage = signal<number>(1);
  hasMore = signal<boolean>(true);

  // Constructor
  constructor(private gameService: GameService, private gamesStore: GamesStoreService) { }

  // OnInit method
  ngOnInit(): void {
    this.loadLastPage();
  }

  // Methods
  // Get the last page number and load saved games
  loadLastPage(): void {
    // Get all games from Store
    const storedGames = this.gamesStore.getGames();
    this.games.set(storedGames);

    // Get last page from Store
    const lastPage = this.gamesStore.getLastLoadedPage();

    // Update the page number or make a request.
    if (lastPage > 0) {
      this.currentPage.set(lastPage);
    } else {
      this.loadNextPage(); // First request
    }
  }

  // Load next page of games
  loadNextPage() {
    // If it is not loading and it is not the last page of the backend, we load the next page.
    if (this.loading() || !this.hasMore()) return;
    if (this.error()) return;

    this.loading.set(true);

    // Service call
    this.gameService.getGamesPage(this.currentPage()).subscribe({
      next: (response) => {
        // Get all games from Store
        this.games.set(this.gamesStore.getGames());

        // Get the current page and the total papges from the backend.
        const currentPage = response.page.number;
        const totalPages = response.page.totalPages;

        // If the current page is less than the total, set hasMore to true and increase the current page in 1
        this.hasMore.set(currentPage < totalPages - 1);

        if (currentPage < totalPages - 1) {
          this.currentPage.set(currentPage + 1);
        }

        this.loading.set(false);
      },
      error: (error) => {
        this.error.set(error);
        this.loading.set(false);
      },
    });
  }

  // Observe the sentinel div to make a new request.
  @ViewChild('sentinel') sentinel!: ElementRef;
  intervalId: any;

  ngAfterViewInit() {
    const observer = new IntersectionObserver(entries => {
      const entry = entries[0];
      if (entry.isIntersecting) {
        // Start loading periodically while visible
        if (!this.intervalId) {
          this.intervalId = setInterval(() => {
            this.loadNextPage();
          }, 100);
        }
      } else {
        // Stops loading when the sentinel leaves view
        if (this.intervalId) {
          clearInterval(this.intervalId);
          this.intervalId = null;
        }
      }
    }, { threshold: 0.1 });

    observer.observe(this.sentinel.nativeElement);
  }
}

