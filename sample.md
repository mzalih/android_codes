# android_codes
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Resources res = getPackageManager().getResourcesForApplication("com.mdm");
            Log.d("LOG", res.getIdentifier("global_admin_page", "layout", "com.mza") + "");
            setTheme(R.style.Theme_Radar);
            ImageButton imv = new ImageButton(getApplicationContext());
            setContentView(imv);

            Drawable img = res.getDrawable(res.getIdentifier("icon_settings", "drawable", "com.mza"), null);
            //imv.setBackground(res.getDrawable(res.getIdentifier("settings_button", "drawable", "com.mza"), null)); //settings_button
           imv.setBackground(doit());
            imv.setImageDrawable(img);
        } catch (Exception e) {
        e.printStackTrace();
        }
    }

        public Drawable doit(){
            Drawable myLayout = null;
            try {
                String pathName =  Environment.getExternalStorageDirectory()
                        .getAbsolutePath() + File.separator + "btn_add.9.png";
                Resources res = getResources();
                Bitmap bitmap = BitmapFactory.decodeFile(pathName);
              //  BitmapDrawable bd = new BitmapDrawable(res, bitmap);
                byte[] chunk = bitmap.getNinePatchChunk();

                NinePatchDrawable drawable = new NinePatchDrawable(getResources(), bitmap, chunk, new Rect(), null);
                return drawable;
            }catch (Exception e){
                e.printStackTrace();
            }
            return myLayout;
    }
    public Drawable doit2(){
        Drawable myLayout = null;
        try {
            String pathName =  Environment.getExternalStorageDirectory()
                    .getAbsolutePath() + File.separator + "nav_big.xml";
            Resources res = getResources();
            Bitmap bitmap = BitmapFactory.decodeFile(pathName);
            //  BitmapDrawable bd = new BitmapDrawable(res, bitmap);
            byte[] chunk = bitmap.getNinePatchChunk();

            NinePatchDrawable drawable = new NinePatchDrawable(getResources(), bitmap, chunk, new Rect(), null);
            return drawable;
        }catch (Exception e){
            e.printStackTrace();
        }
        return myLayout;
    }
    
      public static boolean isServiceRunning(Context context, Class serviceClass) {
        ActivityManager manager =
                (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
    
    
    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
    
    public static float pxToDp(float px) {
        float densityDpi = Resources.getSystem().getDisplayMetrics().densityDpi;
        return px / (densityDpi / 160f);
    }

    public static int dpToPx(int dp) {
        float density = Resources.getSystem().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm =
                (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
    }
