export class PhoneUtils {

  /**
   * Remove any caracter, just number 
   */
  static onlyNumbers(value: string): string {

    if (!value) {
      return '';
    }

    return value.replace(/\D/g, '');

  }

  /**
   * Format to (54)98114-0395
   */
  static format(value: string): string {

    const phone = this.onlyNumbers(value);

    if (phone.length !== 11) {
      return value;
    }

    return phone.replace(
      /(\d{2})(\d{5})(\d{4})/,
      '($1)$2-$3'
    );

  }

}