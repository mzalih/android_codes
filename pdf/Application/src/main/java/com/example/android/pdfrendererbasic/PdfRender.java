package com.example.android.pdfrendererbasic;/***
 * pdf
 * <p/>
 * Author                : toobler- MZALIH(salih@toobler.com)
 * Company               : Toobler
 * Email:                : info@toobler.com
 * Web site              : http://www.toobler.com
 * Created               : 18/07/16
 * Description           : PdfRender .
 * ==============================================================================================
 * Change History:
 * ----------------------------------------------------------------------------------------------
 * Sl.No.  Date   			Author    		Description
 * ----------------------------------------------------------------------------------------------
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.ParcelFileDescriptor;

import java.io.IOException;

/***
 * pdf
 */
public class PdfRender {
    /**
     * File descriptor of the PDF.
     */
    private ParcelFileDescriptor mFileDescriptor;

    /**
     * {@link android.graphics.pdf.PdfRenderer} to render the PDF.
     */
    private PdfRenderer mPdfRenderer;

    /**
     * Page that is currently shown on the screen.
     */
    private PdfRenderer.Page mCurrentPage;
    /**
     * Sets up a {@link android.graphics.pdf.PdfRenderer} and related resources.
     */
    public void openRenderer(Context context,String path) throws IOException {
        // In this sample, we read a PDF from the assets directory.
        mFileDescriptor = context.getAssets().openFd(path).getParcelFileDescriptor();
        // This is the PdfRenderer we use to render the PDF.
        mPdfRenderer = new PdfRenderer(mFileDescriptor);
    }
    /**
     * Closes the {@link android.graphics.pdf.PdfRenderer} and related resources.
     *
     * @throws java.io.IOException When the PDF file cannot be closed.
     */
    public void closeRenderer() throws IOException {
        if (null != mCurrentPage) {
            mCurrentPage.close();
        }
        mPdfRenderer.close();
        mFileDescriptor.close();
    }

    public Bitmap getPage(int i,Context ctx){
        try {
            Bitmap m = renderPage(i);
            return m;
        }catch (Exception e){

        }
        return  null;
    }
    public int getPageCount(){
        if (mPdfRenderer == null) {
            return 0;
        }
        return mPdfRenderer.getPageCount();
    }

    /**
     * Shows the specified page of PDF to the screen.
     *
     * @param index The page index.
     */
    private Bitmap renderPage(int index) {
        if (mPdfRenderer.getPageCount() <= index) {
            return null;
        }
        // Make sure to close the current page before opening another one.
        if (null != mCurrentPage) {
            mCurrentPage.close();
        }
        // Use `openPage` to open a specific page in PDF.
        mCurrentPage = mPdfRenderer.openPage(index);
        // Important: the destination bitmap must be ARGB (not RGB).
        Bitmap bitmap = Bitmap.createBitmap(mCurrentPage.getWidth(), mCurrentPage.getHeight(),
                Bitmap.Config.ARGB_8888);
        // Here, we render the page onto the Bitmap.
        // To render a portion of the page, use the second and third parameter. Pass nulls to get
        // the default result.
        // Pass either RENDER_MODE_FOR_DISPLAY or RENDER_MODE_FOR_PRINT for the last parameter.
        mCurrentPage.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
        // We are ready to show the Bitmap to user.
       return bitmap;
    }
}
