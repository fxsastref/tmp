import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ForeignUserFriendsList } from './foreign-user-friends-list';

describe('ForeignUserFriendsList', () => {
  let component: ForeignUserFriendsList;
  let fixture: ComponentFixture<ForeignUserFriendsList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ForeignUserFriendsList]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ForeignUserFriendsList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
