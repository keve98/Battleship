import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-verify-not-successful',
  templateUrl: './verify-not-successful.component.html',
  styleUrls: ['./verify-not-successful.component.css']
})
export class VerifyNotSuccessfulComponent implements OnInit {

  code: string = "";

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  public verify():void{
    this.code = (<HTMLInputElement>document.getElementById('code')).value;
    this.router.navigate([`https://localhost:4200/#/verify/${this.code}`]);
  }

}
