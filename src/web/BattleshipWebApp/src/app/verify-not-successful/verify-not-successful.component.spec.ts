import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VerifyNotSuccessfulComponent } from './verify-not-successful.component';

describe('VerifyNotSuccessfulComponent', () => {
  let component: VerifyNotSuccessfulComponent;
  let fixture: ComponentFixture<VerifyNotSuccessfulComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VerifyNotSuccessfulComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VerifyNotSuccessfulComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
