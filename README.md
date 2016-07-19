# android_codes
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Resources res = getPackageManager().getResourcesForApplication("com.tapendium.mdm");
            Log.d("LOG", res.getIdentifier("global_admin_page", "layout", "com.tapendium.mdm") + "");
            setTheme(R.style.Theme_Radisson);
            ImageButton imv = new ImageButton(getApplicationContext());
            setContentView(imv);

            Drawable img = res.getDrawable(res.getIdentifier("icon_settings", "drawable", "com.tapendium.mdm"), null);
            //imv.setBackground(res.getDrawable(res.getIdentifier("settings_button", "drawable", "com.tapendium.mdm"), null)); //settings_button
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
