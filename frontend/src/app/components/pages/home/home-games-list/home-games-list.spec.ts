import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeGamesList } from './home-games-list';

describe('HomeGamesList', () => {
  let component: HomeGamesList;
  let fixture: ComponentFixture<HomeGamesList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HomeGamesList]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomeGamesList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
