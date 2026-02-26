import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountBanner } from './account-banner';

describe('AccountBanner', () => {
  let component: AccountBanner;
  let fixture: ComponentFixture<AccountBanner>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AccountBanner]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AccountBanner);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
