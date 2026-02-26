import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ForeignUserTabs } from './foreign-user-tabs';

describe('ForeignUserTabs', () => {
  let component: ForeignUserTabs;
  let fixture: ComponentFixture<ForeignUserTabs>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ForeignUserTabs]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ForeignUserTabs);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
