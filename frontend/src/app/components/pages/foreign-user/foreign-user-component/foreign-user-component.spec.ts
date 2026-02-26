import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ForeignUserComponent } from './foreign-user-component';

describe('ForeignUserComponent', () => {
  let component: ForeignUserComponent;
  let fixture: ComponentFixture<ForeignUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ForeignUserComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ForeignUserComponent);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
