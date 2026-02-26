import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ForeignUserGamesList } from './foreign-user-games-list';

describe('ForeignUserGamesList', () => {
  let component: ForeignUserGamesList;
  let fixture: ComponentFixture<ForeignUserGamesList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ForeignUserGamesList]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ForeignUserGamesList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
