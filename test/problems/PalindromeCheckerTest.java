package problems;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PalindromeCheckerTest {

        @Test
        public void testPalindromeWithSimplePalindrome() {
            assertTrue(PalindromeChecker.isPalindrome("madam"));
        }

        @Test
        public void testPalindromeWithMixedCasePalindrome() {
            assertTrue(PalindromeChecker.isPalindrome("Madam"));
        }

        @Test
        public void testPalindromeWithPunctuationAndSpaces() {
            assertTrue(PalindromeChecker.isPalindrome("A man, a plan, a canal, Panama"));
        }

        @Test
        public void testPalindromeWithNonPalindrome() {
            assertFalse(PalindromeChecker.isPalindrome("Hello, World!"));
        }

        @Test
        public void testPalindromeWithEmptyString() {
            assertTrue(PalindromeChecker.isPalindrome(""));
        }

        @Test
        public void testPalindromeWithNullInput() {
            assertFalse(PalindromeChecker.isPalindrome(null));
        }

        @Test
        public void testPalindromeWithNumericPalindrome() {
            assertTrue(PalindromeChecker.isPalindrome("12321"));
        }

        @Test
        public void testPalindromeWithNumericNonPalindrome() {
            assertFalse(PalindromeChecker.isPalindrome("12345"));
        }

}
