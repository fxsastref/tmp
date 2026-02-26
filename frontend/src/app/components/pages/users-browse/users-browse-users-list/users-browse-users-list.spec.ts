import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsersBrowseUsersList } from './users-browse-users-list';

describe('UsersBrowseUsersList', () => {
  let component: UsersBrowseUsersList;
  let fixture: ComponentFixture<UsersBrowseUsersList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UsersBrowseUsersList]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UsersBrowseUsersList);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
