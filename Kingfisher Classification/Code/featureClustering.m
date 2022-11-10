clear
%This script demonstrates the processing and analysis of audio files 
%containinng Shaldag's syllibles, using a variety of specially designed
%tools as well as matlab native tools and algorithms.

%iterating all files and extracting features
[dataNewLog,dataNewLin,dataMel,origin,vect] = IterateSilliblesSub('All syllables - devideD');
save('Processed','dataNewLog','dataNewLin','dataMel','origin','vect');
%load ('Processed.mat');

K=9;
maxIter=3000;
%uncomment the next line to view SOM 
%newSom(origin,vect,25,K,0.9,4,50000); 
%newSom(dataNewLog(:,22:end),vect,20,K,1.3,5,20000);
% *** NEW MEL - LOG SPACINGS *** - newMel(csv,fs,melF);
figure
subplot(221)
Y1=dataNewLog;
[newMedoidVecNewLog,~,~]=newKMedoids(Y1,K,maxIter);      %get newMedoid clustering
kmeansVecNewLog=kmeans(Y1,K);                            %get kmeans clustering
str='New mel and data combined (Log)';
plotClustering(Y1,vect,newMedoidVecNewLog,kmeansVecNewLog,str,K); %plot clustering regular KMedoid
%uncomment next line for SOM activation using kmeans clustering
%newSom(dataNewLog,kmeansVecNewLog,25,K,0.9,4,50000); 
%uncomment next line for SOM activation using kmedoids clustering
%newSom(dataNewLog,newMedoidVecNewLog,25,K,0.9,4,50000); 


% ** plot tsne **
figure
subplot(121)              
Y1=tsne(Y1);
gscatter(Y1(:,1),Y1(:,2),kmeansVecNewLog)
title('From kmeans')
ylabel('New mel and data combined (Log)','FontWeight','Bold','FontSize',15)%kmeans silhouette
subplot(122)
gscatter(Y1(:,1),Y1(:,2),newMedoidVecNewLog)
title('From medoids')
ylabel('New mel and data combined (Log)','FontWeight','Bold','FontSize',15)%kmeans silhouette


figure           % *** NEW MEL - LINEAR SPACINGS *** - newFilters(csv,fs,melF);
subplot(221)
Y2=dataNewLin;
[newMedoidVecNewLinear,~,~]=newKMedoids(Y2,K,maxIter);              %get medoid clustering
kmeansVecNew=kmeans(Y2,K);                %get kmeans clustering
str='New mel and data combined (Linear)';
plotClustering(Y2,vect,kmeansVecNew,newMedoidVecNewLinear,str,K);
%uncomment next line for SOM activation using kmeans clustering
%newSom(dataNewLin,kmeansVecNew,25,K,0.9,4,50000); 
%uncomment next line for SOM activation using kmedoids clustering
%newSom(dataNewLin,newMedoidVecNewLinear,25,K,0.9,4,50000); 


% ** plot tsne **
figure
subplot(121)                               
Y2=tsne(Y2);
gscatter(Y2(:,1),Y2(:,2),kmeansVecNew)
title('From kmeans')
ylabel('New mel and data combined (Linear)','FontWeight','Bold','FontSize',15)

subplot(122)
gscatter(Y2(:,1),Y2(:,2),newMedoidVecNewLinear)
title('From kmedoids')
ylabel('New mel and data combined (Linear)','FontWeight','Bold','FontSize',15)


figure           % ** STANDARD MELCEPST *** - v_melcepst(csv,fs,'Mt',12,melF,length(csv))
subplot(221)
Y3=dataMel;
[newMedoidVecMel,~,~] = newKMedoids(Y3,K,maxIter);                
kmeansVecMel=kmeans(Y3,K);                                        
str='Standard melcepst data combined';
plotClustering(Y3,vect,kmeansVecMel,newMedoidVecMel,str,K);
%uncomment next line for SOM activation using kmeans clustering
%newSom(dataMel,kmeansVecMel,25,K,0.9,4,50000); 
%uncomment next line for SOM activation using kmedoids clustering
%newSom(dataMel,newMedoidVecMel,25,K,0.9,4,50000); 

% ** plot tsne **
figure
subplot(121)
Y3=tsne(Y3);
gscatter(Y3(:,1),Y3(:,2),kmeansVecMel)
title('From kmeans')
ylabel('Standard melcepst data combined','FontWeight','Bold','FontSize',15)%kmeans silhouette

subplot(122)
gscatter(Y3(:,1),Y3(:,2),newMedoidVecMel)
title('From medoids')
ylabel('Standard melcepst data combined','FontWeight','Bold','FontSize',15)%kmeans silhouette


figure            %%%%  RAW NUMERICAL DATA ONLY  %%%
subplot(221)
newMedoidVecRaw=newKMedoids(origin,K,maxIter);                        %get medoid clustering
kmeansVecRaw=kmeans(origin,K);                                        %get kmeans clustering
str='Original - raw numerical data only';
plotClustering(origin,vect,kmeansVecRaw,newMedoidVecRaw,str,K);
%uncomment next line for SOM activation using kmeans clustering
%newSom(origin,kmeansVecRaw,25,K,0.9,4,50000); 
%uncomment next line for SOM activation using kmedoids clustering
%newSom(origin,newMedoidVecRaw,25,K,0.9,4,50000); 


% ** plot tsne **
figure
subplot(121)
Y3=tsne(Y3);
gscatter(Y3(:,1),Y3(:,2),kmeansVecRaw)
title('From kmeans')
ylabel('Original - raw numerical data only','FontWeight','Bold','FontSize',15)%kmeans silhouette
subplot(122)
gscatter(Y3(:,1),Y3(:,2),newMedoidVecRaw)
title('From medoids')
ylabel('Original - raw numerical data only','FontWeight','Bold','FontSize',15)%kmeans silhouette

