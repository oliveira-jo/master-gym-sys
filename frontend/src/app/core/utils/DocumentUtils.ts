export class DocumentUtils {

  static onlyNumbers(value: string): string {

    if (!value) {
      return '';
    }

    return value.replace(/\D/g, '');

  }

  static formatCpf(value: string): string {

    const cpf = this.onlyNumbers(value);

    return cpf.replace(
      /(\d{3})(\d{3})(\d{3})(\d{2})/,
      '$1.$2.$3-$4'
    );

  }

}