import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AchievementCardListed } from './achievement-card-listed';

describe('AchievementCardListed', () => {
  let component: AchievementCardListed;
  let fixture: ComponentFixture<AchievementCardListed>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AchievementCardListed]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AchievementCardListed);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
