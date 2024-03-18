import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddInvoice2Component } from './add-invoice2.component';

describe('AddInvoice2Component', () => {
  let component: AddInvoice2Component;
  let fixture: ComponentFixture<AddInvoice2Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddInvoice2Component ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddInvoice2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
