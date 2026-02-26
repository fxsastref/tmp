import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AccountFriendsList } from './account-friends-list';

describe('AccountFriendsList', () => {
  let component: AccountFriendsList;
  let fixture: ComponentFixture<AccountFriendsList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AccountFriendsList]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AccountFriendsList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
