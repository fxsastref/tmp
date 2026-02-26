import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GameCardListed } from './game-card-listed';

describe('GameCardListed', () => {
  let component: GameCardListed;
  let fixture: ComponentFixture<GameCardListed>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GameCardListed]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GameCardListed);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
