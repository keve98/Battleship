import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerifySuccessfulComponent } from './verify-successful.component';

describe('VerifySuccessfulComponent', () => {
  let component: VerifySuccessfulComponent;
  let fixture: ComponentFixture<VerifySuccessfulComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VerifySuccessfulComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VerifySuccessfulComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
