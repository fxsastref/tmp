import { Component, OnInit, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { initFlowbite } from 'flowbite';
import { Navbar } from "./components/layout/navbar/navbar";
import { Footer } from "./components/layout/footer/footer";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Navbar, Footer],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App implements OnInit {
  protected readonly title = signal('platinum');

  // OnInit method
  ngOnInit(): void {
    // Flowbite initialize
    initFlowbite();
  }
}
