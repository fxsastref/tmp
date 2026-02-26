import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GamesBrowseHero } from './games-browse-hero';

describe('GamesBrowseHero', () => {
  let component: GamesBrowseHero;
  let fixture: ComponentFixture<GamesBrowseHero>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GamesBrowseHero]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GamesBrowseHero);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
