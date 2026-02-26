import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GamesBrowseGamesList } from './games-browse-games-list';

describe('GamesBrowseGamesList', () => {
  let component: GamesBrowseGamesList;
  let fixture: ComponentFixture<GamesBrowseGamesList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GamesBrowseGamesList]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GamesBrowseGamesList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
