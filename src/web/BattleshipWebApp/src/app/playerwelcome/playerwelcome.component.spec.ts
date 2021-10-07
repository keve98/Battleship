import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlayerwelcomeComponent } from './playerwelcome.component';

describe('PlayerwelcomeComponent', () => {
  let component: PlayerwelcomeComponent;
  let fixture: ComponentFixture<PlayerwelcomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PlayerwelcomeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PlayerwelcomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
