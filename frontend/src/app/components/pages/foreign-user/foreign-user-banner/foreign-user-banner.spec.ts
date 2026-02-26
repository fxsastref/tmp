import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ForeignUserBanner } from './foreign-user-banner';

describe('ForeignUserBanner', () => {
  let component: ForeignUserBanner;
  let fixture: ComponentFixture<ForeignUserBanner>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ForeignUserBanner]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ForeignUserBanner);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
