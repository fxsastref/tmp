import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GameAchievements } from './game-achievements';

describe('GameAchievements', () => {
  let component: GameAchievements;
  let fixture: ComponentFixture<GameAchievements>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GameAchievements]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GameAchievements);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
