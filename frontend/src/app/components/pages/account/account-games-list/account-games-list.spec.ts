import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountGamesList } from './account-games-list';

describe('AccountGamesList', () => {
  let component: AccountGamesList;
  let fixture: ComponentFixture<AccountGamesList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AccountGamesList]
    })
      .compileComponents();

    fixture = TestBed.createComponent(AccountGamesList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
