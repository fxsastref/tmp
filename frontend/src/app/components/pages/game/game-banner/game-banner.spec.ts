import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GameBanner } from './game-banner';

describe('GameBanner', () => {
  let component: GameBanner;
  let fixture: ComponentFixture<GameBanner>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GameBanner]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GameBanner);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
