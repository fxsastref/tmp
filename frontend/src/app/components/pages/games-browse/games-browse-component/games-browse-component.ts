import { Component } from '@angular/core';
import { GamesBrowseGamesList } from "../games-browse-games-list/games-browse-games-list";
import { GamesBrowseHero } from "../games-browse-hero/games-browse-hero";

@Component({
  selector: 'app-games-browse-component',
  imports: [GamesBrowseGamesList, GamesBrowseHero],
  templateUrl: './games-browse-component.html',
  styleUrl: './games-browse-component.scss',
})
export class GamesBrowseComponent { }