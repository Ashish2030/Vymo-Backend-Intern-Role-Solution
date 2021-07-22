int largestSubgrid(vector<vector<int>> grid, int maxSum){
    int n=grid.size();
    vector<vector<int>> sum(n, vector<int> (n, 0));
    int mx=0;
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            if(i==0 && j==0){
                sum[0][0]=grid[0][0];
            }
            else if(i==0){
                sum[0][j]=sum[0][j-1]+grid[0][j];
            }
            else if(j==0){
                sum[i][0]=sum[i-1][0]+grid[i][0];
            }
            else{
                sum[i][j]=sum[i-1][j]+sum[i][j-1]+grid[i][j]-sum[i-1][j-1];
            }
            mx=max(mx, grid[i][j]);
            // cout << sum[i][j] <<' ';
        }
        // cout <<endl;
    }
    // if(maxSum < mx)return 0;
    // if(maxSum >= sum[n-1][n-1])return n;
    int ans=0;
    int l=0, r=n;
    while(l<r){
        int x=l+(r-l+1)/2;
        int res=0;
        for(int i=x-1;i<n;i++){
            for(int j=x-1;j<n;j++){
                int  total=sum[i][j];
                if(i>=x)total-=sum[i-x][j];
                if(j>=x)total-=sum[i][j-x];
                if(i>=x && j>=x)total+=sum[i-x][j-x];
                res=max(res, total);
            }
        }
        if(maxSum >= res)l=x;
        else r=x-1;
    }
    return r;
