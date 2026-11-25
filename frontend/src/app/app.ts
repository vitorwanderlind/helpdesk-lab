
import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';

import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatChipsModule } from '@angular/material/chips';

@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet,
    MatButtonModule,
    MatCardModule,
    MatIconModule,
    MatChipsModule
  ],
  templateUrl: './app.html',
  styleUrl: './app.scss',
})
export class App {

  protected readonly title = signal('HelpDesk Lab');

  protected readonly descricao = signal(
    'Sistema de gerenciamento de chamados'
  );

  protected readonly contador = signal(0);

  protected incrementar(): void {
    this.contador.update(valor => valor + 1);
  }

  protected decrementar(): void {
    this.contador.update(valor => Math.max(0, valor - 1));
  }

  protected zerar(): void {
    this.contador.set(0);
  }

}
