import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GamesBrowseComponent } from './games-browse-component';

describe('GamesBrowseComponent', () => {
  let component: GamesBrowseComponent;
  let fixture: ComponentFixture<GamesBrowseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GamesBrowseComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GamesBrowseComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
