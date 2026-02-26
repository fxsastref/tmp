import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsersBrowseComponent } from './users-browse-component';

describe('UsersBrowseComponent', () => {
  let component: UsersBrowseComponent;
  let fixture: ComponentFixture<UsersBrowseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UsersBrowseComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UsersBrowseComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
