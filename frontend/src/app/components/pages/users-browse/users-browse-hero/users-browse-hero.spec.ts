import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsersBrowseHero } from './users-browse-hero';

describe('UsersBrowseHero', () => {
  let component: UsersBrowseHero;
  let fixture: ComponentFixture<UsersBrowseHero>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UsersBrowseHero]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UsersBrowseHero);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
