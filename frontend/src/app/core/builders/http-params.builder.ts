import { HttpParams } from '@angular/common/http';

export class HttpParamsBuilder {

  static build(object: Record<string, any>): HttpParams {

    let params = new HttpParams();

    Object.entries(object).forEach(([key, value]) => {

      if (
        value !== null &&
        value !== undefined &&
        value !== ''
      ) {

        params = params.set(key, value);

      }

    });

    return params;

  }

}

// Observe que ele ignora automaticamente:
// null
// undefined
// string vazia
// Isso já resolve 95% dos filtros.

